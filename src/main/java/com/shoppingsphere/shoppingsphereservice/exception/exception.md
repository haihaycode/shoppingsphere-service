Việc tạo các lỗi (Exception) tùy chỉnh (custom) là một kỹ thuật quan trọng trong lập trình.
Thông qua việc tùy chỉnh các Exception, lập trình viên có thể thể hiện rõ ràng hơn về những
gì có thể sai trong mã code của mình, và giúp người khác (hoặc chính họ trong tương lai) dễ dàng debug và bảo dưỡng code hơn.

public class MyCustomException extends Exception{
public MyCustomException(String message){
super(message);
}
}
Trong đoạn mã trên, MyCustomException là một lớp Custom Exception mới. Khi bạn muốn ném ra một Exception tùy chỉnh, bạn có thể tạo một đối tượng mới từ lớp này và ném nó ra bằng cách sử dụng từ khóa throw như sau:
java
throw new MyCustomException("This is a custom message");