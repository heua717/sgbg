// SPDX-License-Identifier: GPL-3.0
pragma solidity >=0.7.0;

import "@openzeppelin/contracts/utils/Strings.sol";

interface SingleBungleInterface {
    function enterRoom() external payable; // 입장하기
    function leaveRoom() external payable; // 퇴장하기
    function withdraw() external payable; // 방장이 출금하기
    // 성공/실패 리뷰 저장
}

contract SingleBungle is SingleBungleInterface {
    // uint randNonce = 0;

    address private host; // 방장
    uint private memberLength; // 참여자 수

    uint private MINIMUM_AMOUNT; // 최소 모금액 결정 (0.01 ETH == 1e16)
    uint private closeDate; // 모금 마감 기간

    mapping(address => uint) memberToMoney; // key-value 쌍의 hash table

    constructor(address _host, uint _duration, uint _minimumAmount) {
        host = _host; // 방장 설정
        closeDate = block.timestamp + _duration; // 모금 마감 기간
        MINIMUM_AMOUNT = _minimumAmount; // 1인당 최소 모금액 설정
    }

    // 지불 가능한 참가자인가
    modifier onlyPayableMember() {
        require(msg.value >= MINIMUM_AMOUNT, string.concat(string.concat("MINIMUM AMOUNT: ", Strings.toString(MINIMUM_AMOUNT)), " ETH"));
        _;
    }

    // 모금 기한 내의 모금인가
    modifier onlyOpenDate() {
        require(block.timestamp < closeDate, "ROOM CLOSED");
        _;
    }

    // 입장하기: 참가자 추가 + 참가자 ETH 지불
    function enterRoom() external payable
    onlyPayableMember
    onlyOpenDate {
        memberLength++; // add member
        memberToMoney[msg.sender] = msg.value; // save who send eth
        payable(address(this)).transfer(msg.value); // send eth from member to contract
        // address(this).transfer(msg.value);
    }

    // 해당 방 유저인 경우
    modifier onlyRoomMember() {
        require(memberToMoney[msg.sender] > 0, "NOT A ROOM MEMBER");
        _;
    }

    // 퇴장하기: 참가자 삭제 + 참가자가 송금했던 ETH 출금
    function leaveRoom() external payable
    onlyRoomMember
    onlyOpenDate {
        memberLength--; // delete member
        payable(msg.sender).transfer(memberToMoney[msg.sender]); // send eth from contract to member
        delete memberToMoney[msg.sender]; // delete who send eth
    }

    // 방장만 ETH 출금 가능
    modifier onlyHost() {
        require(msg.sender == host, "NOT HOST ADDRESS");
        _;
    }

    // 모집 마감되었을 경우
    modifier onlyAfterRoomCloses() {
        require(block.timestamp > closeDate, "ROOM NOT CLOSED YET");
        _;
    }

    // 출금하기: 모집이 성공적으로 마감되었을 때, ETH 출금
    function withdraw() public payable
    onlyHost
    onlyAfterRoomCloses {
        payable(msg.sender).transfer(address(this).balance); // send all eth from contract to member
    }

    // 현재 contract가 보유하고 있는 ETH 반환
    function getBalance() public view returns(uint256) {
        if(address(this) == address(0)) return 0;
        return address(this).balance;
    }

    // function selectRandomFunder() public returns(address, uint) {
    //     uint mod = members.length;
    //     if(mod == 0) {
    //         return (address(0), 0);
    //     }

    //     uint random = uint(keccak256(abi.encodePacked(block.timestamp, msg.sender, randNonce))) % mod;
    //     randNonce++;
        
    //     address randomFunderAddress = members[random];
    //     return (randomFunderAddress, memberToMoney[randomFunderAddress]);
    // }
}