package com.wangjx.pms.service;

import com.wangjx.pms.mapper.DiaryMapper;
import com.wangjx.pms.model.DiaryModel;
import com.wangjx.pms.pojo.Diary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tigeeer
 * Date: 2017/11/17
 * Time: 17:33
 */
@Service
public class DiaryService {

    private DiaryMapper diaryMapper;

    public DiaryService(DiaryMapper diaryMapper) {
        this.diaryMapper = diaryMapper;
    }

    public void insert(Diary diary) {
        diaryMapper.insert(diary);
    }

    public List<DiaryModel> getByProjectPeriodId(Long projectPeriodId) {
        return diaryMapper.findByProjectPeriodId(projectPeriodId);
    }
}
