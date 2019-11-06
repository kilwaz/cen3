import {Directive, HostBinding, HostListener} from "@angular/core";
import {WebSocketService} from "./services/websocket.service";

@Directive({
  selector: "[dnd]"
})
export class DndDirective {
  @HostBinding("style.background") private background = "#eee";

  webSocketService: WebSocketService;

  constructor(private webSocketServiceConst: WebSocketService) {
    this.webSocketService = webSocketServiceConst;
  }

  @HostListener("dragover", ["$event"]) onDragOver(evt) {
    evt.preventDefault();
    evt.stopPropagation();
    this.background = "#999";
  }

  @HostListener("dragleave", ["$event"])
  public onDragLeave(evt) {
    evt.preventDefault();
    evt.stopPropagation();
    this.background = "#eee";
  }

  @HostListener("drop", ["$event"])
  public onDrop(evt) {
    evt.preventDefault();
    evt.stopPropagation();
    let files: FileList = evt.dataTransfer.files;
    if (files.length > 0) {
      this.background = "#eee";

      if (typeof Worker !== "undefined") {
        // Create a new
        const worker = new Worker("./app.worker.ts", {type: "module"});

        worker.onmessage = ({data}) => {
          this.webSocketService.sendByteBuffer(data);
        };

        worker.postMessage(files);
      } else {
        // Web Workers are not supported in this environment.
        // You should add a fallback so that your program still executes correctly.
      }
    }
  }
}
