package bitcamp.myapp.vo;

public class Page {
    private int currentPage; // 현재 페이지 번호
    private int totalPages; // 전체 페이지 수
    private int prev; // 이전 페이지 번호
    private int next; // 다음 페이지 번호

    // 생성자, getter, setter 등 필요한 메서드를 추가할 수 있습니다.

    // Getter 및 Setter 메서드
    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPrev() {
        return prev;
    }

    public void setPrev(int prev) {
        this.prev = prev;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }
}