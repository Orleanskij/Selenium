import org.junit.Assert;
import org.junit.Test;
import shop.VirtualItem;

public class VirtualItemTests {

    @Test
    public void checkVirtualItemClass() {
        VirtualItem disk = new VirtualItem();
        disk.setName("Windows");
        disk.setPrice(11);
        disk.setSizeOnDisk(20000);
        boolean result = 32000 > disk.getSizeOnDisk();
        Assert.assertTrue(result);
    }
}