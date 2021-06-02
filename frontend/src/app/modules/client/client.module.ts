import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';
import { ClientComponent } from './client.component';
import { ClientRoutingModule } from './client-routing.module';
import { OrderComponent } from './order/order.component';
import { OrdersComponent } from './orders/orders.component';
import { TopProductsComponent } from './top-products/top-products.component';
import { PickpocketingFormComponent } from './order/pickpocketing-form/pickpocketing-form.component';
import { NgxsModule } from '@ngxs/store';
import { ClientState } from '@state/client/client.state';
import { OrderService } from '@services/order.service';
import { TitleService } from '@services/title.service';
import { OrderGuard } from '@guards/order.guard';

@NgModule({
  declarations: [
    ClientComponent,
    OrderComponent,
    OrdersComponent,
    TopProductsComponent,
    PickpocketingFormComponent
  ],
  imports: [
    SharedModule,
    ClientRoutingModule,
    NgxsModule.forFeature([ClientState])
  ],
  providers: [
    OrderService,
    TitleService,
    OrderGuard
  ]
})
export class ClientModule { }
