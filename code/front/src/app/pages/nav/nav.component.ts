import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {EventEmitterConfig} from '../../config/event-emitter.config';

@Component({
    selector: 'app-nav',
    templateUrl: './nav.component.html',
    styleUrls: ['./nav.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class NavComponent implements OnInit {

    title: string;

    constructor(
        private eventEmitterConfig: EventEmitterConfig
    ) {
        this.title = '';
        eventEmitterConfig.getNavTitle().subscribe((value) => {
            this.title = value;
        });
    }

    ngOnInit() {
    }

}
