import {Directive, HostBinding, HostListener} from '@angular/core';
import {WebSocketService} from './services/websocket.service';

@Directive({
  // tslint:disable-next-line:directive-selector
  selector: '[dnd]'
})
export class DndDirective {
  @HostBinding('style.background') private background = '#eee';

  webSocketService: WebSocketService;

  constructor(private webSocketServiceConst: WebSocketService) {
    this.webSocketService = webSocketServiceConst;
  }

  @HostListener('dragover', ['$event']) onDragOver(evt) {
    evt.preventDefault();
    evt.stopPropagation();
    this.background = '#999';
  }

  @HostListener('dragleave', ['$event'])
  public onDragLeave(evt) {
    evt.preventDefault();
    evt.stopPropagation();
    this.background = '#eee';
  }

  @HostListener('drop', ['$event'])
  public onDrop(evt) {
    evt.preventDefault();
    evt.stopPropagation();
    const files: FileList = evt.dataTransfer.files;
    if (files.length > 0) {
      this.background = '#eee';

      if (typeof Worker !== 'undefined') {
        // Create a new worker to deal with our task
        console.log('Starting worker..');
        const worker = new Worker(new URL('./app.worker.ts', import.meta.url), {type: 'module'});
        worker.postMessage(files);
      } else {
        // Web Workers are not supported in this environment.
        // You should add a fallback so that your program still executes correctly.
      }
    }
  }
}
