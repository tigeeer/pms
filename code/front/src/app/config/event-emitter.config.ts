import { Injectable, Output, EventEmitter } from '@angular/core';

@Injectable()
export class EventEmitterConfig {
    @Output() showSidebar: EventEmitter<any> = new EventEmitter();
    @Output() showNav: EventEmitter<any> = new EventEmitter();
    @Output() navTitle: EventEmitter<any> = new EventEmitter();

    constructor() {}

    setShowSidebar(value: boolean) {
        this.showSidebar.emit(value);
    }

    getShowSidebar() {
        return this.showSidebar;
    }

    setShowNav(value: boolean) {
        this.showNav.emit(value);
    }

    getShowNav() {
        return this.showNav;
    }

    setNavTitle(value: string) {
        this.navTitle.emit(value);
    }

    getNavTitle() {
        return this.navTitle;
    }
}
