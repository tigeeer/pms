import { Component, OnInit } from '@angular/core';
import { NameConfig } from '../../config/name.config';

@Component({
    selector: 'app-sidebar',
    templateUrl: './sidebar.component.html',
    styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

    nameConfig: any;

    constructor() {
        this.nameConfig = new NameConfig();
    }

    ngOnInit() {
    }
}
