package com.example.myproj.Service;

import java.util.List;
import java.util.Optional;

import com.example.myproj.Exception.BlogAlreadyExistsException;
import com.example.myproj.Exception.BlogNotFoundException;
import com.example.myproj.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myproj.Repository.BlogRepository;

@Service
public class BlogServiceImpl implements BlogService{
	
	private BlogRepository blogRepository;
	
	@Autowired
	public BlogServiceImpl(BlogRepository blogRepository)
	{
		this.blogRepository=blogRepository;
	}

	@Override
	public Blog SaveBlog(Blog blog) throws BlogAlreadyExistsException {
		// TODO Auto-generated method stub
		if(blogRepository.existsById(blog.getBlogId()))
		{
			throw new BlogAlreadyExistsException();

		}
	else
		{
			Blog SavedBlog = blogRepository.save(blog);
			return SavedBlog;
		}
	}

	@Override
	public List<Blog> getAllBlogs() {
		// TODO Auto-generated method stub
		
		return(List<Blog>) blogRepository.findAll();
		
	}

	@Override
	public Blog getBlogById(int id) throws BlogNotFoundException {
		// TODO Auto-generated method stub
		Blog blog = null;

		Optional optional =blogRepository.findById(id);
		if(optional.isPresent()) {
			blog = blogRepository.findById(id).get();
		}
//		}
		else{
			throw new BlogNotFoundException();
		}
		return blog;


	}

	@Override
	public Blog deleteById(int id) throws BlogNotFoundException {
		// TODO Auto-generated method stub
	Blog blog=null;
		Optional optional = blogRepository.findById(id);
		if(optional.isPresent())
		{
			blog = blogRepository.findById(id).get();
			blogRepository.deleteById(id);
		}
else{
	throw new BlogNotFoundException();
		}
		return blog;
	}

	@Override
	public Blog updateBlog(Blog blog) throws BlogAlreadyExistsException,BlogNotFoundException {
		// TODO Auto-generated method stub
		
		Blog UpdatedBlog= null;
		boolean bool = blogRepository.existsById(blog.getBlogId());
		if(bool) {
			Blog ActualBlog = getBlogById(blog.getBlogId());
			ActualBlog.setAuthorName(blog.getAuthorName());
			UpdatedBlog = blogRepository.save(ActualBlog);
		}
		else
		{
			throw new BlogNotFoundException();
		}
		return UpdatedBlog;
	}
	
	
}
