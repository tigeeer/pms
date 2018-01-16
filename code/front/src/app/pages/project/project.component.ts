import { Component, OnInit } from '@angular/core';
import { EventEmitterConfig } from '../../config/event-emitter.config';
import { NameConfig } from '../../config/name.config';

@Component({
    selector: 'app-project',
    templateUrl: './project.component.html',
    styleUrls: ['./project.component.scss']
})
export class ProjectComponent implements OnInit {

    nameConfig: any;

    constructor(
        private eventEmitterConfig: EventEmitterConfig
    ) {
        this.nameConfig = new NameConfig();
        this.eventEmitterConfig.setShowSidebar(true);
        this.eventEmitterConfig.setNavTitle(this.nameConfig.navProject);
    }

    ngOnInit() {
    }

}
