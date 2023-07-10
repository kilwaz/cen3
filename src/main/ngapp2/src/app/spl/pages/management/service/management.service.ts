import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {WebSocketService} from '../../../services/websocket.service';
import {Management} from "../../../wsActions/management";
import {DownloadTest} from "../../../wsActions/downloadTest";
import {ExportConfiguration} from "../../../wsActions/exportConfiguration";
import {RecalculateHierarchy} from "../../../wsActions/recalculateHierarchy";

@Injectable()
export class ManagementService {
  constructor(private webSocketService: WebSocketService) {
  }

  queryManagement(): Observable<Management> {
    const managementAction: Management = new Management();

    return this.webSocketService.sendWithObservable(managementAction) as Observable<Management>;
  }

  downloadTest(): Observable<DownloadTest> {
    const downloadTestAction: DownloadTest = new DownloadTest();

    return this.webSocketService.sendWithObservable(downloadTestAction) as Observable<DownloadTest>;
  }

  downloadConfig(): Observable<ExportConfiguration> {
    const exportConfigurationAction: ExportConfiguration = new ExportConfiguration();

    return this.webSocketService.sendWithObservable(exportConfigurationAction) as Observable<ExportConfiguration>;
  }

  recalculateHierarchy(): Observable<RecalculateHierarchy> {
    const recalculateHierarchyAction: RecalculateHierarchy = new RecalculateHierarchy();

    return this.webSocketService.sendWithObservable(recalculateHierarchyAction) as Observable<RecalculateHierarchy>;
  }
}
