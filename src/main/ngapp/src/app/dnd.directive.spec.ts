import { DndDirective } from './dnd.directive';
import {WebSocketService} from "./services/websocket.service";

describe('DndDirective', () => {
  it('should create an instance', () => {
    const directive = new DndDirective(null);
    expect(directive).toBeTruthy();
  });
});
