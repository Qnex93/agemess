/**
 * Created with IntelliJ IDEA.
 * User: Qnex
 * Date: 01.10.13
 * Time: 0:08
 * To change this template use File | Settings | File Templates.
 */
public class Program {
    public static void main(String[] args) throws Exception {
        Communication server = Communication.create(777);
        server.run();
    }
}
