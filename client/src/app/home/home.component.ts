import {Component} from '@angular/core';

@Component({
    templateUrl: 'home.component.html'
})
export class HomeComponent {
    public text: string;

    constructor() {
        this.text = 'MILES: Jazz In The Bay';
    }
}
