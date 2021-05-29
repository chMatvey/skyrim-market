import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

interface ComponentWithLoading {
  loading: boolean
}

function withLoading$<R, C extends ComponentWithLoading>(request$: Observable<R>, component: C) {
  component.loading = true
  return request$
    .pipe(
      finalize(() => component.loading = false)
    )
}

export function withLoading<T, C extends ComponentWithLoading>(component: C): (source$: Observable<T>) => Observable<T> {
  return source$ => withLoading$<T, C>(source$, component)
}
