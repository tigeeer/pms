import { Component, NgZone, OnInit } from '@angular/core';
import { EventEmitterConfig } from '../../config/event-emitter.config';
import { NameConfig } from '../../config/name.config';
import { UserModel } from '../../model/user.model';
import { TableModel } from '../../component/table/table.model';

@Component({
    selector: 'app-user',
    templateUrl: './user.component.html',
    styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {

    nameConfig: any;
    selectedUsers: number[];
    users: UserModel[];
    userTable: TableModel;

    constructor(
        private ngZone: NgZone,
        private eventEmitterConfig: EventEmitterConfig
    ) {
        this.users = [];
        this.selectedUsers = [];
        this.userTable = new TableModel();
        this.nameConfig = new NameConfig();
        this.eventEmitterConfig.setShowSidebar(true);
        this.eventEmitterConfig.setNavTitle(this.nameConfig.navUser);
    }

    getUsers(offset: number, size: number) {
        this.users = [];

        for (let i = 0; i < size; i++) {
            const user = new UserModel();
            user.id = offset + i;
            user.name = 'name' + user.id;
            user.roleName = '管理员';
            this.users.push(user);
        }

        this.userTable.data = this.users;
    }

    makeTable() {
        this.userTable.total = 1001;
        this.userTable.head = [
            {key: 'id', text: 'ID'},
            {key: 'name', text: '姓名'},
            {key: 'roleName', text: '角色'}
        ];

        this.userTable.onSelect = (index) => {
            if (index === -1) {
                this.selectedUsers = [];

                this.users.forEach((item) => {
                    if (this.userTable.checkAll) {
                        item.checked = false;
                    } else {
                        item.checked = true;
                        this.selectedUsers.push(item.id);
                    }
                });
            } else {
                const u = this.users[index];

                if (u.checked) {
                    this.selectedUsers.splice(this.selectedUsers.indexOf(u.id), 1);
                } else {
                    this.selectedUsers.push(u.id);
                }

                u.checked = !u.checked;
            }

            this.userTable.checkAll = this.users.length === this.selectedUsers.length;
        };

        this.userTable.onChangeData = (offset, size) => {
            this.getUsers(offset, size);
        };
    }

    ngOnInit() {
        this.makeTable();
    }
}
