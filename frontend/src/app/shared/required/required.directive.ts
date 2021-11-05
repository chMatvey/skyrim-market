import { Directive, ElementRef, OnInit } from '@angular/core';

@Directive({
  selector: '[appRequired]'
})
export class RequiredDirective implements OnInit {

  constructor(private el: ElementRef) { }

  ngOnInit() {
    this.el.nativeElement.insertAdjacentHTML('beforeend', `
      <span class="required">*</span>
    `)
  }
}
