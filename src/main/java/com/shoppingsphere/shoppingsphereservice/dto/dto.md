--
java
@Entity
public class User {
    @Id @GeneratedValue
    private Long id;
    private String username;
    private String email;
    private String password; // Thông tin nhạy cảm
    private Date birthdate;
    // Các phương thức getter và setter tương ứng
}
Điển hình, chúng ta không muốn trả về password khi gửi thông tin user đến client. Do đó, chúng ta có thể tạo một UserDTO:
java
public class UserDTO {
    private String username;
    private String email;
    private Date birthdate;

    // Các phương thức getter và setter tương ứng

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.birthdate = user.getBirthdate();
    }
}
Khi chúng ta muốn gửi thông tin user đến client, chúng ta sẽ tạo một UserDTO từ User:
java
User user = userRepository.findById(id);
UserDTO userDTO = new UserDTO(user);
return userDTO;
Như vậy, DTO đã giúp chúng ta kiểm soát chính xác những thông tin nào được trả về cho client.