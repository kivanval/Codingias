import { Directive, ViewContainerRef } from '@angular/core';

@Directive({
    selector: '[inputDir]',
})
export class InputDirective {
    constructor(public viewContainerRef: ViewContainerRef) { }
}

