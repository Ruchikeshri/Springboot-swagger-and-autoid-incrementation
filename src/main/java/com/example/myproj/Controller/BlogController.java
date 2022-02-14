package com.example.myproj.Controller;

import java.util.List;

import com.example.myproj.Exception.BlogAlreadyExistsException;
import com.example.myproj.Exception.BlogNotFoundException;

import com.example.myproj.Service.BlogSequenceGenerator;
import com.example.myproj.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.myproj.Service.BlogService;


@RestController
@RequestMapping(value="/api/v1/")
public class BlogController {
	
	
	private BlogService blogService;
	@Autowired
	private BlogSequenceGenerator sequenceGenerator;
	
	@Autowired
	public BlogController(BlogService blogService)
	{
		this.blogService=blogService;
	}
	@PostMapping("blog")
	public  ResponseEntity<Blog> SaveBlog(@RequestBody Blog blog) throws BlogAlreadyExistsException
	{
		blog.setBlogId((int) sequenceGenerator.generateBlogSequence(Blog.SEQUENCE_NAME));
//		Blog SavedBlog=blogService.SaveBlog(blog);

		return new ResponseEntity<>(blogService.SaveBlog(blog),HttpStatus.OK);
	}

	@GetMapping("blogs")
	public ResponseEntity<List<Blog>> getBlogs()
	{
		return new ResponseEntity<List<Blog>>((List<Blog>) blogService.getAllBlogs(),HttpStatus.OK);
	}
	
	@PutMapping("blog")
	public ResponseEntity<Blog> updateBlog(@RequestBody Blog blog)throws BlogAlreadyExistsException, BlogNotFoundException
	{
		return new ResponseEntity<Blog>(blogService.updateBlog(blog), HttpStatus.OK);
	}
//
	@GetMapping("blog/{BlogId}")
	public ResponseEntity<Blog> getBlogId(@PathVariable("BlogId") int blogId ) throws BlogNotFoundException
	{
		return new ResponseEntity<Blog>(blogService.getBlogById(blogId),HttpStatus.OK);
	}

//	@GetMapping("blog")
//	public ResponseEntity<Blog> getBlogById(@RequestParam int BlogId) throws BlogNotFoundException
//	{
//		return new ResponseEntity<Blog>(blogService.getBlogById(BlogId),HttpStatus.OK);
//	}
//
//@GetMapping("blog/{BlogId}")
//public ResponseEntity<Blog> getBlogById(@PathVariable("BlogId") int blogId) throws BlogNotFoundException
//{
//	return new ResponseEntity<Blog>(blogService.getBlogById(blogId),HttpStatus.OK);
//}
	@DeleteMapping("blog/{blogId}")
	public ResponseEntity<Blog> DeleteId(@PathVariable int blogId) throws BlogNotFoundException
	{
		return new ResponseEntity<Blog>(blogService.deleteById(blogId),HttpStatus.OK);
	}
}

