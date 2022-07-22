import { Directive, ViewContainerRef } from '@angular/core';

@Directive({
    selector: '[answerDir]',
})
export class AnswerDirective {
    constructor(public viewContainerRef: ViewContainerRef) { }
}

