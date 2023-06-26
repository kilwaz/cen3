package requests.spark.websockets.objects.messages.dataitems;

import requests.spark.websockets.objects.JSONWeb;
import requests.spark.websockets.objects.messages.mapping.WSData;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;

public class WorksheetStatus extends JSONWeb {
    @WSDataReference(WSData.WORKSHEET_STATUS_NAME)
    private String worksheetName;

    @WSDataReference(WSData.WORKSHEET_STATUS_HEAD_COUNT)
    private Integer headCount;

    @WSDataReference(WSData.WORKSHEET_STATUS_TOTAL_PAGES)
    private Integer totalPages;

    @WSDataReference(WSData.WORKSHEET_STATUS_CURRENT_PAGE_NUMBER)
    private Integer currentPageNumber;

    @WSDataReference(WSData.WORKSHEET_STATUS_PAGE_SIZE)
    private Integer pageSize;

    public WorksheetStatus() {
    }

    public String getWorksheetName() {
        return worksheetName;
    }

    public void setWorksheetName(String worksheetName) {
        this.worksheetName = worksheetName;
    }

    public Integer getHeadCount() {
        return headCount;
    }

    public void setHeadCount(Integer headCount) {
        this.headCount = headCount;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getCurrentPageNumber() {
        return currentPageNumber;
    }

    public void setCurrentPageNumber(Integer currentPageNumber) {
        this.currentPageNumber = currentPageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
