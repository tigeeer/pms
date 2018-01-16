import { Component, OnInit } from '@angular/core';

import { EventEmitterConfig } from '../../config/event-emitter.config';
import { NameConfig } from '../../config/name.config';
import {BaseConfig} from '../../config/base.config';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

    nameConfig: any;

    constructor(
        private eventEmitterConfig: EventEmitterConfig
    ) {
        this.nameConfig = new NameConfig();
        this.eventEmitterConfig.setShowSidebar(true);
        this.eventEmitterConfig.setNavTitle(this.nameConfig.navHome);
    }

    ngOnInit() {
        console.log(BaseConfig.baseUrl);
    }
}
