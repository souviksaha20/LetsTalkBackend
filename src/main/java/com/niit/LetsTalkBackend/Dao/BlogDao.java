package com.niit.LetsTalkBackend.Dao;

import java.util.List;

import com.niit.LetsTalkBackend.Model.BlogComment;
import com.niit.LetsTalkBackend.Model.BlogPost;
import com.niit.LetsTalkBackend.Model.User;

public interface BlogDao {
	
	List<BlogPost> getBlogPosts();
	BlogPost getBlogPost(int id);
	BlogPost addBlogPost(User user,BlogPost blogPost);
	List<BlogComment> getBlogComments(int blogId);
	BlogPost addBlogPostComment(User user,BlogComment blogComment);
	

}
