package com.zhaoyani.springboot.service.impl;

import com.zhaoyani.springboot.entity.Article;
import com.zhaoyani.springboot.mapper.ArticleMapper;
import com.zhaoyani.springboot.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZYN
 * @since 2023-09-09
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

}
