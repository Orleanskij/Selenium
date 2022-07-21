import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import shop.VirtualItem;

public class VirtualItemTests {

    @Test
    public void testVirtualItem() {
        VirtualItem disk = new VirtualItem();
        disk.setSizeOnDisk(20000);
        Assertions.assertTrue(disk.toString().contains("20000"));
    }
}