package com.netclip.netclips.service.mapper;

import com.netclip.netclips.domain.Comment;
import com.netclip.netclips.service.dto.CommentDTO;
import org.springframework.stereotype.Service;

@Service
public class CommentMapper {

    public CommentDTO CommentToDTO(Comment comment) {
        return new CommentDTO(
            comment.getId(),
            comment.getContent(),
            comment.getTimeStamp(),
            comment.getLikes(),
            comment.getDislikes(),
            comment.getVideoUser().getId(),
            comment.getVideoUser().getInternalUser().getLogin(),
            comment.getVideo().getId()
        );
    }
}
