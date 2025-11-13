package delft;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import java.util.*;
import java.util.stream.*;

import static java.util.Collections.*;
import static org.assertj.core.api.Assertions.*;
import static delft.Field.*;
import static delft.Property.*;
import static delft.SportsHallPlanner.planHalls;

class SportsHallPlannerTests {

    @Test
    public void testConstructorRequest(){
        Request request = new Request(singleton(CLOSE_PUBLIC_TRANSPORT), BADMINTON, 1);
        assertThat(request.getProperties()).containsExactly(CLOSE_PUBLIC_TRANSPORT);
        assertThat(request.getRequiredFieldType()).isEqualTo(BADMINTON);
        assertThat(request.getMinNumberOfFields()).isEqualTo(1);
    }

    @Test
    public void testConstructorRequestNoProperties(){
        Request request = new Request(null, BADMINTON, 1);
        assertThat(request.getProperties()).isNull();
        assertThat(request.getRequiredFieldType()).isEqualTo(BADMINTON);
        assertThat(request.getMinNumberOfFields()).isEqualTo(1);
    }

    @Test
    public void testSportHallEquals(){
        SportsHall hall = new SportsHall(Set.of(CLOSE_PUBLIC_TRANSPORT), Map.of(BADMINTON, 1));
        SportsHall hall1 = new SportsHall(Set.of(CLOSE_PUBLIC_TRANSPORT), Map.of(BADMINTON, 1));
        assertThat(hall).isEqualTo(hall1);
    }

    @Test
    public void testPlanHalls(){
        List<Request> requests = List.of(new Request(singleton(CLOSE_PUBLIC_TRANSPORT), BADMINTON, 1));
        List<SportsHall> halls = List.of(new SportsHall(singleton(CLOSE_PUBLIC_TRANSPORT), Map.of(BADMINTON, 1)));
        Map<SportsHall, Request> solution = planHalls(requests, halls);
        assertThat(solution).containsEntry(halls.get(0), requests.get(0));
    }

    @Test
    public void testCanFulfillRequest(){
        SportsHall hall = new SportsHall(singleton(CLOSE_PUBLIC_TRANSPORT), Map.of(BADMINTON, 1));
        Request request = new Request(singleton(CLOSE_PUBLIC_TRANSPORT), BADMINTON, 1);
        assertThat(hall.canFulfillRequest(request)).isTrue();
    }

    @Test
    public void testCanFulfillRequestWithError(){
        try {
            SportsHall hall = new SportsHall(null, Map.of(BADMINTON, 1));
            hall.canFulfillRequest(null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(NullPointerException.class);
        }
    }

}
