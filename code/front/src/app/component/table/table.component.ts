import { Component, OnInit, Input } from '@angular/core';
import { TableModel } from './table.model';

@Component({
    selector: 'app-table',
    templateUrl: './table.component.html',
    styleUrls: ['./table.component.scss']
})
export class TableComponent implements OnInit {

    @Input() tableModel: TableModel;

    pageTotal: number;
    currentPage: number;
    pageRow: number;
    pageRowArray: any;

    constructor() {
        this.currentPage = 1;
        this.pageRowArray = [5, 10, 15, 20];
        this.pageRow = this.pageRowArray[0];
    }

    changePageRow(pageRow: number) {
        this.currentPage = 1;
        this.pageRow = pageRow;

        this.refresh();
    }

    changeCurrentPage(currentPage: number) {
        if (currentPage >= 1 && currentPage <= this.pageTotal) {
            this.currentPage = currentPage;
        }

        this.refresh();
    }

    onSelect(index) {
        this.tableModel.onSelect(index);
    }

    refresh() {
        this.tableModel.onChangeData((this.currentPage - 1) * this.pageRow, this.pageRow);
    }

    ngOnInit() {
        this.pageTotal = Math.ceil(this.tableModel.total / this.pageRow);
        this.refresh();
    }

}
