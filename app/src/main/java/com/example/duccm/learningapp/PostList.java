package com.example.duccm.learningapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duccm on 8/9/17.
 */

public class PostList {
    private List<Post> postList;

    public List<Post> getPostList() {
        return postList;
    }

    public PostList() {
        postList = new ArrayList<>();
        Post post = new Post(0, "Chao buoi sang", "https://westaway.co/wp-content/uploads/2015/12/4.jpg", "01/07/2017");
        postList.add(post);

        post = new Post(1, "Chao buoi chieu", "", "02/07/2017");
        postList.add(post);

        post = new Post(2, "Chao buoi toi", "", "03/07/2017");
        postList.add(post);

        post = new Post(3, "Xin chao moi nguoi", "", "04/07/2017");
        postList.add(post);

        post = new Post(4, "Hoc hanh vat va ma ket qua...", "", "05/07/2017");
        postList.add(post);

        post = new Post(0, "Chan doi qua...", "", "06/07/2017");
        postList.add(post);

        post = new Post(1, "Hihi", "", "07/07/2017");
        postList.add(post);

        post = new Post(2, "Haha", "", "08/07/2017");
        postList.add(post);

        post = new Post(3, "Huhu", "", "09/07/2017");
        postList.add(post);

        post = new Post(4, "Thua", "", "10/07/2017");
        postList.add(post);

        post = new Post(0, "Dep trai khoai to khong lo chet doi", "", "11/07/2017");
        postList.add(post);

        post = new Post(1, "Co gang hoc gioi", "", "12/07/2017");
        postList.add(post);

        post = new Post(2, "Ok I'm fine", "", "13/07/2017");
        postList.add(post);

        post = new Post(3, "Ai solo k", "", "14/07/2017");
        postList.add(post);

        post = new Post(4, "Hong dien thoai rui", "", "15/07/2017");
        postList.add(post);

        post = new Post(0, "Hay lam", "", "16/07/2017");
        postList.add(post);

        post = new Post(1, "Xinh qua", "", "17/07/2017");
        postList.add(post);

        post = new Post(2, "Buon qua", "", "18/07/2017");
        postList.add(post);

        post = new Post(3, "Hoc bai di cac em", "", "19/07/2017");
        postList.add(post);

        post = new Post(4, "Ngon vl", "", "20/07/2017");
        postList.add(post);
    }
}
