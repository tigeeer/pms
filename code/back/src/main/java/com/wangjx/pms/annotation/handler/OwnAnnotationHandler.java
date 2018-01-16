package com.wangjx.pms.annotation.handler;

import com.wangjx.common.exception.CommonException;
import com.wangjx.pms.annotation.own.OwnParam;
import com.wangjx.pms.annotation.own.OwnProject;
import com.wangjx.pms.annotation.own.OwnUser;
import com.wangjx.pms.pojo.Project;
import com.wangjx.pms.security.AuthenticationFactory;
import com.wangjx.pms.service.ProjectService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: tigeeer
 * Date: 2017/11/3
 * Time: 11:06
 */
@Aspect
@Component
public class OwnAnnotationHandler {

    private static final Logger logger = LoggerFactory.getLogger(OwnAnnotationHandler.class);

    private ProjectService projectService;

    public OwnAnnotationHandler(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Pointcut("execution(@com.wangjx.pms.annotation.own.* * *(..))")
    public void own() {}

    @Before(value = "own()")
    public void logBefore(JoinPoint joinPoint) {
        Long currentUserId = AuthenticationFactory.getUserId();

        if (currentUserId == null) {
            throw new CommonException("No permission.");
        }

        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        Class<?>[] classes = Arrays.stream(args).map(Object::getClass).toArray(Class<?>[]::new);

        try {
            Method method = Class.forName(className).getMethod(methodName, classes);
            Parameter[] parameters = method.getParameters();
            Annotation[] methodAnnotations = method.getAnnotations();
            OwnParam ownParam = null;
            Object arg = null;

            for (int i = 0; i < parameters.length; i++) {
                Parameter parameter = parameters[i];

                if (parameter.isAnnotationPresent(OwnParam.class)) {
                    ownParam = parameter.getAnnotation(OwnParam.class);
                    arg = args[i];

                    break;
                }
            }

            if (ownParam == null) {
                throw new CommonException("Not found class OwnParam.");
            }

            for (Annotation annotation : methodAnnotations) {
                Class annotationClass = annotation.getClass();
                String fieldName = ownParam.value();
                Long param;

                if (fieldName.equals("")) {
                    param = (Long) arg;
                } else {
                    Field field = arg.getClass().getDeclaredField(fieldName);
                    field.setAccessible(true);
                    param = (Long) field.get(arg);
                }

                if (param == null) {
                    throw new CommonException(String.format("Field '%s' is Null.", fieldName));
                }

                if (annotationClass.equals(OwnUser.class)) {
                    if (currentUserId.longValue() != param) {
                        throw new CommonException("No permission.");
                    }
                }

                if (annotationClass.equals(OwnProject.class)) {
                    Project project = projectService.getById(param);

                    if (project == null) {
                        throw new CommonException("Not found project.");
                    }

                    Long userId = project.getUserId();

                    if (currentUserId.longValue() != userId) {
                        throw new CommonException("No permission.");
                    }
                }
            }
        } catch (NoSuchMethodException | ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
