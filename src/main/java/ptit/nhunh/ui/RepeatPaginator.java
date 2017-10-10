package ptit.nhunh.ui;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class RepeatPaginator {

    private static final int DEFAULT_RECORDS_NUMBER = 10;
    private static final int DEFAULT_PAGE_INDEX = 1;

    private int records;
    private int recordsTotal;
    private int pageIndex;
    private int pages;
    private List<?> origModel;
    private List<?> model;
    @Getter
    @Setter
    private int offset;
    @Getter
    @Setter
    private int limit;
    
    
    public RepeatPaginator(List<?> model) {
        this.origModel = model;
        this.records = DEFAULT_RECORDS_NUMBER;
        this.pageIndex = DEFAULT_PAGE_INDEX;        
        this.recordsTotal = model.size();

        if (this.records > 0) {
            this.pages = this.records <= 0 ? 1 : this.recordsTotal / this.records;

            if (this.recordsTotal % this.records > 0) {
                this.pages++;
            }

            if (this.pages == 0) {
                this.pages = 1;
            }
        } else {
            this.records = 1;
            this.pages = 1;
        }

        this.updateModel();
    }

    public void updateModel() {
        int fromIndex = this.getFirst();
        int toIndex = this.getFirst() + this.records;

        if(toIndex > this.recordsTotal) {
            toIndex = this.recordsTotal;
        }

        this.limit = toIndex;
        this.offset = fromIndex + 1;
        
        this.model = this.origModel.subList(fromIndex, toIndex);
    }

    public void next() {
        if(this.pageIndex < this.pages) {
            this.pageIndex++;
        }

        this.updateModel();
    }

    public void prev() {
        if(this.pageIndex > 1) {
            this.pageIndex--;
        }

        this.updateModel();
    }   

    public int getRecords() {
        return this.records;
    }

    public int getRecordsTotal() {
        return this.recordsTotal;
    }

    public int getPageIndex() {
        return this.pageIndex;
    }

    public int getPages() {
        return this.pages;
    }

    public int getFirst() {
        return (this.pageIndex * this.records) - this.records;
    }

    public List<?> getModel() {
        return this.model;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

}