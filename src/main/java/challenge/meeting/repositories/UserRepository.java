package challenge.meeting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import challenge.meeting.domains.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByemail(String email);
}
