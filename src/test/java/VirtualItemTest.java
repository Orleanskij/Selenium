import org.testng.Assert;
import org.testng.annotations.Test;
import shop.VirtualItem;

public class VirtualItemTest {

    @Test(groups = "regression")
    public void testVirtualItem() {
        VirtualItem disk = new VirtualItem();
        disk.setSizeOnDisk(20000);
        Assert.assertTrue(disk.toString().contains("20000"));
    }
}