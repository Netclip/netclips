package com.netclip.netclips.service.impl;

import com.netclip.netclips.domain.Comment;
import com.netclip.netclips.repository.CommentRepository;
import com.netclip.netclips.service.CommentService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Comment}.
 */
@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private final Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment save(Comment comment) {
        log.debug("Request to save Comment : {}", comment);
        return commentRepository.save(comment);
    }

    @Override
    public Comment update(Comment comment) {
        log.debug("Request to save Comment : {}", comment);
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getAllByVideo(Long videoId, int pageNo, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());

        Page<Comment> pagedRes = commentRepository.findAllByVideo_Id(videoId, pageable);

        if (pagedRes.hasContent()) {
            return pagedRes.getContent();
        }
        return new ArrayList<>();
    }

    @Override
    public Optional<Comment> partialUpdate(Comment comment) {
        log.debug("Request to partially update Comment : {}", comment);

        return commentRepository
            .findById(comment.getId())
            .map(existingComment -> {
                if (comment.getTimeStamp() != null) {
                    existingComment.setTimeStamp(comment.getTimeStamp());
                }
                if (comment.getContent() != null) {
                    existingComment.setContent(comment.getContent());
                }
                if (comment.getLikes() != null) {
                    existingComment.setLikes(comment.getLikes());
                }
                if (comment.getDislikes() != null) {
                    existingComment.setDislikes(comment.getDislikes());
                }

                return existingComment;
            })
            .map(commentRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findAll() {
        log.debug("Request to get all Comments");
        return commentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Comment> findOne(Long id) {
        log.debug("Request to get Comment : {}", id);
        return commentRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Comment : {}", id);
        commentRepository.deleteById(id);
    }
}
