import { ChangeDetectionStrategy, Component } from '@angular/core';

@Component({
  selector: 'app-top-products',
  templateUrl: './top-products.component.html',
  styleUrls: ['./top-products.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class TopProductsComponent {
}
