package exs1;

public class Client {

    public static void main(String[] args) throws Exception {
    HttpAction response=new HttpAction();
    String url = "https://reqres.in//api/users/2";
    System.out.println(response.get(url)+'\n');

    String json = """
            {
                "name": "morpheus",
                "job": "leader"
            }
            """;
    System.out.println(response.post("https://reqres.in//api/users",json)+'\n');

    response.delete(url);

    json = """
            {
                "name": "morpheus",
                "job": "zion resident"
            }""";

    System.out.println(response.put(url,json));

    System.out.println(response.patch(url,json));

    }

}



