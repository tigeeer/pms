import { BaseModel } from './base.model';

export class UserModel extends BaseModel {
    id: number;
    name: string;
    roleName: string;

    constructor() {
        super();
        this.checked = false;
    }
}
