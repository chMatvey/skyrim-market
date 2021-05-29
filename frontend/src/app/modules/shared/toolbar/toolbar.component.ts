import { Component, OnInit } from '@angular/core';
import { Select } from '@ngxs/store';
import { Observable } from 'rxjs';
import { AppState } from '../../../state/app.state';
import { ToolbarLink } from '../../../models/toolbar-link';

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.scss']
})
export class ToolbarComponent implements OnInit {

  @Select(AppState.username)
  username$: Observable<string>

  @Select(AppState.toolbarLinks)
  toolbarLinks$: Observable<ToolbarLink[]>

  constructor() { }

  ngOnInit(): void {
  }

}
