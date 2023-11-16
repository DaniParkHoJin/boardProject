package org.parkhojin.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.parkhojin.commons.constans.MemberType;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes = {
        @Index(name = "idx_member_userNm", columnList = "userNm"),
        @Index(name = "idx_member_mobile", columnList = "mobile")
})
public class Member extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userNo;

    @Column(length = 65,unique = true, nullable = false)
    private String email;

    @Column(length = 65, name = "pw", nullable = false)
    private String password;

    @Column(length = 40, nullable = false)
    private String userNm;

    @Column(length = 11, unique = true)
    private String mobile;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private MemberType mtype = MemberType.USER;

}