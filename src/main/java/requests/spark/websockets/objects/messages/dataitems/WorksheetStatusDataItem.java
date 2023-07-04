package requests.spark.websockets.objects.messages.dataitems;

import requests.spark.websockets.objects.JSONWeb;
import requests.spark.websockets.objects.messages.mapping.WSDataReference;

public class WorksheetStatusDataItem extends JSONWeb {
    @WSDataReference()
    private String worksheetName;

    @WSDataReference()
    private Integer headCount;

    @WSDataReference()
    private Integer totalPages;

    @WSDataReference()
    private Integer currentPageNumber;

    @WSDataReference()
    private Integer pageSize;

    public WorksheetStatusDataItem() {
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
