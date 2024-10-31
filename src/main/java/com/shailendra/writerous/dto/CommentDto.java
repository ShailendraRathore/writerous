package com.shailendra.writerous.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {
    private Long id;
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @NotEmpty(message = "Email cannot be empty")
    @Email
    private String email;
    @NotEmpty(message = "Content cannot be empty")
    private String content;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
