import { Injectable } from '@angular/core';
import { HttpUtil } from '../util/http.util';
import { BaseConfig } from '../config/base.config';

@Injectable()
export class UserService {

    static userUrl = BaseConfig.baseUrl + '/user';
    static currentUserUrl = UserService.userUrl + '/current';
    static userListUrl = UserService.userUrl + '/many';

    constructor(
        private httpUtil: HttpUtil
    ) {
    }

    getCurrentUser(): Promise<any> {
        return this.httpUtil.get(UserService.currentUserUrl);
    }

    getUser(id: number): Promise<any> {
        const url = UserService.userUrl + '?id=' + id;
        return this.httpUtil.get(url);
    }

    getUserList(offset: number, size: number): Promise<any> {
        const url = UserService.userListUrl + '?offset=' + offset + '&size=' + size;
        return this.httpUtil.get(url);
    }
}
