export interface Message<T = any> {
  title: string,
  body: string
  data: T
}
