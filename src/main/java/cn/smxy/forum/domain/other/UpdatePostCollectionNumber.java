package cn.smxy.forum.domain.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePostCollectionNumber {
    private Long postId;
    private Long collectionNumber;
}
