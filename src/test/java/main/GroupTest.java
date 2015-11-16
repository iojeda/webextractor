package main;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Ismael Ojeda Perez on 15/11/2015.
 */
public class GroupTest {

    Group group = new Group();

    @Test
    public void nameGroupTest(){
        group.setName("DRR Test");
        Assert.assertEquals(group.getName(),"DRR Test");
    }


}