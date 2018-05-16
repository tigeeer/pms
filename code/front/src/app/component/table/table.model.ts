import { TableHeadModel } from './table-head.model';

export class TableModel {
    total: number;
    head: TableHeadModel[];
    data: any[];
    checkAll: boolean;
    checkbox: boolean;
    onSelect: Function;
    onChangeData: Function;
}
