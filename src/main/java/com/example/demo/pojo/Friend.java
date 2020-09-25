package com.example.demo.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author Gilbert
 * @date 2020/9/25 16:41
 */
@Document(collection = "friend")
@Data
@Alias("Friend")
public class Friend {
    /**
     * 记录id
     */
    @Id
    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 该用户好友
     */
    private List<Integer> friendId;
}
