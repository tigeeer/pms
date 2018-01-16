import { Component } from '@angular/core';
import { EventEmitterConfig } from './config/event-emitter.config';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent {

    showSidebar: boolean;

    constructor(
        private eventEmitterConfig: EventEmitterConfig
    ) {
        this.showSidebar = false;
        this.eventEmitterConfig.getShowSidebar().subscribe((value) => {
            this.showSidebar = value;
        });
    }
}
