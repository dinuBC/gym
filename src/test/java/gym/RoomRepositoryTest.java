package gym;

import com.gym.model.Location;
import com.gym.model.Room;
import com.gym.repository.RoomRepository;
import org.junit.Test;

import java.util.LinkedHashSet;
import java.util.Set;

public class RoomRepositoryTest {
    @Test
    public void generateRoomDBTest() {
        Set<Room> testSet = new LinkedHashSet<>();
        Location testLocation = new Location();
        testLocation.setId(32);

        Room room1 = new Room();
        room1.setId(1);
        room1.setCapacity(20);
        room1.setLocation(testLocation);

        Room room2 = new Room();
        room2.setId(2);
        room2.setCapacity(10);
        room2.setLocation(testLocation);

        testSet.add(room1);
        testSet.add(room2);

        RoomRepository.generateRoomDB(testSet);
    }
}
