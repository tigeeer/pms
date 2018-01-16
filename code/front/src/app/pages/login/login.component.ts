import { Component, OnInit } from '@angular/core';

import { EventEmitterConfig } from '../../config/event-emitter.config';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

    constructor(
        private eventEmitterConfig: EventEmitterConfig
    ) {
        eventEmitterConfig.setShowSidebar(false);
    }

    ngOnInit() {
    }

}
