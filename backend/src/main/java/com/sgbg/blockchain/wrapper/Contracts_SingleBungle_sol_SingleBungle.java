package com.sgbg.blockchain.wrapper;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class Contracts_SingleBungle_sol_SingleBungle extends Contract {
    public static final String BINARY = "60806040523480156200001157600080fd5b50604051620011663803806200116683398181016040528101906200003791906200016e565b836000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555082600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508060038190555050505050620001e0565b600080fd5b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000620000fb82620000ce565b9050919050565b6200010d81620000ee565b81146200011957600080fd5b50565b6000815190506200012d8162000102565b92915050565b6000819050919050565b620001488162000133565b81146200015457600080fd5b50565b60008151905062000168816200013d565b92915050565b600080600080608085870312156200018b576200018a620000c9565b5b60006200019b878288016200011c565b9450506020620001ae878288016200011c565b9350506040620001c18782880162000157565b9250506060620001d48782880162000157565b91505092959194509250565b610f7680620001f06000396000f3fe6080604052600436106100555760003560e01c806312065fe01461005a57806351cff8d914610085578063698a14fe146100a157806392cfe9dc146100ca578063b0f707dc146100f5578063c39e76cc14610111575b600080fd5b34801561006657600080fd5b5061006f61012d565b60405161007c919061085a565b60405180910390f35b61009f600480360381019061009a91906108d8565b610172565b005b3480156100ad57600080fd5b506100c860048036038101906100c3919061093d565b6102a8565b005b3480156100d657600080fd5b506100df610310565b6040516100ec91906109dc565b60405180910390f35b61010f600480360381019061010a9190610a23565b610334565b005b61012b60048036038101906101269190610a23565b61051e565b005b60008073ffffffffffffffffffffffffffffffffffffffff163073ffffffffffffffffffffffffffffffffffffffff160361016b576000905061016f565b4790505b90565b80600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1614610203576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016101fa90610ac0565b60405180910390fd5b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166323b872dd3084476040518463ffffffff1660e01b815260040161026093929190610aef565b6020604051808303816000875af115801561027f573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906102a39190610b3b565b505050565b600015158115150361030c5780600560008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff0219169083151502179055505b5050565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6003543410156103456003546106e1565b6040516020016103559190610bff565b6040516020818303038152906040526040516020016103749190610c4b565b604051602081830303815290604052906103c4576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016103bb9190610cbb565b60405180910390fd5b50600260008154809291906103d890610d0c565b919050555080600460008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055506001600560008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff02191690831515021790555060008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166323b872dd8330846040518463ffffffff1660e01b81526004016104d693929190610aef565b6020604051808303816000875af11580156104f5573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906105199190610b3b565b505050565b816000600460008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054116105a1576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161059890610da0565b60405180910390fd5b600260008154809291906105b490610dc0565b919050555060008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166323b872dd3085600460008873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020546040518463ffffffff1660e01b815260040161065593929190610aef565b6020604051808303816000875af1158015610674573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906106989190610b3b565b50600460008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009055505050565b606060008203610728576040518060400160405280600181526020017f3000000000000000000000000000000000000000000000000000000000000000815250905061083c565b600082905060005b6000821461075a57808061074390610d0c565b915050600a826107539190610e18565b9150610730565b60008167ffffffffffffffff81111561077657610775610e49565b5b6040519080825280601f01601f1916602001820160405280156107a85781602001600182028036833780820191505090505b5090505b60008514610835576001826107c19190610e78565b9150600a856107d09190610eac565b60306107dc9190610edd565b60f81b8183815181106107f2576107f1610f11565b5b60200101907effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916908160001a905350600a8561082e9190610e18565b94506107ac565b8093505050505b919050565b6000819050919050565b61085481610841565b82525050565b600060208201905061086f600083018461084b565b92915050565b600080fd5b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b60006108a58261087a565b9050919050565b6108b58161089a565b81146108c057600080fd5b50565b6000813590506108d2816108ac565b92915050565b6000602082840312156108ee576108ed610875565b5b60006108fc848285016108c3565b91505092915050565b60008115159050919050565b61091a81610905565b811461092557600080fd5b50565b60008135905061093781610911565b92915050565b6000806040838503121561095457610953610875565b5b6000610962858286016108c3565b925050602061097385828601610928565b9150509250929050565b6000819050919050565b60006109a261099d6109988461087a565b61097d565b61087a565b9050919050565b60006109b482610987565b9050919050565b60006109c6826109a9565b9050919050565b6109d6816109bb565b82525050565b60006020820190506109f160008301846109cd565b92915050565b610a0081610841565b8114610a0b57600080fd5b50565b600081359050610a1d816109f7565b92915050565b60008060408385031215610a3a57610a39610875565b5b6000610a48858286016108c3565b9250506020610a5985828601610a0e565b9150509250929050565b600082825260208201905092915050565b7f4e4f5420484f5354204144445245535300000000000000000000000000000000600082015250565b6000610aaa601083610a63565b9150610ab582610a74565b602082019050919050565b60006020820190508181036000830152610ad981610a9d565b9050919050565b610ae98161089a565b82525050565b6000606082019050610b046000830186610ae0565b610b116020830185610ae0565b610b1e604083018461084b565b949350505050565b600081519050610b3581610911565b92915050565b600060208284031215610b5157610b50610875565b5b6000610b5f84828501610b26565b91505092915050565b7f4d494e494d554d20414d4f554e543a2000000000000000000000000000000000815250565b600081519050919050565b600081905092915050565b60005b83811015610bc2578082015181840152602081019050610ba7565b60008484015250505050565b6000610bd982610b8e565b610be38185610b99565b9350610bf3818560208601610ba4565b80840191505092915050565b6000610c0a82610b68565b601082019150610c1a8284610bce565b915081905092915050565b7f2045544800000000000000000000000000000000000000000000000000000000815250565b6000610c578284610bce565b9150610c6282610c25565b60048201915081905092915050565b6000601f19601f8301169050919050565b6000610c8d82610b8e565b610c978185610a63565b9350610ca7818560208601610ba4565b610cb081610c71565b840191505092915050565b60006020820190508181036000830152610cd58184610c82565b905092915050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b6000610d1782610841565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8203610d4957610d48610cdd565b5b600182019050919050565b7f4e4f54204120524f4f4d204d454d424552000000000000000000000000000000600082015250565b6000610d8a601183610a63565b9150610d9582610d54565b602082019050919050565b60006020820190508181036000830152610db981610d7d565b9050919050565b6000610dcb82610841565b915060008203610dde57610ddd610cdd565b5b600182039050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601260045260246000fd5b6000610e2382610841565b9150610e2e83610841565b925082610e3e57610e3d610de9565b5b828204905092915050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b6000610e8382610841565b9150610e8e83610841565b9250828203905081811115610ea657610ea5610cdd565b5b92915050565b6000610eb782610841565b9150610ec283610841565b925082610ed257610ed1610de9565b5b828206905092915050565b6000610ee882610841565b9150610ef383610841565b9250828201905080821115610f0b57610f0a610cdd565b5b92915050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fdfea264697066735822122031e9270968805e46e114d7fb7391c796aeb7888a40ae6dd3529ccb14da53591864736f6c63430008110033";

    public static final String FUNC_ENTERROOM = "enterRoom";

    public static final String FUNC_GETBALANCE = "getBalance";

    public static final String FUNC_ISSUCCESS = "isSuccess";

    public static final String FUNC_LEAVEROOM = "leaveRoom";

    public static final String FUNC_SGBGTOKEN = "sgbgToken";

    public static final String FUNC_WITHDRAW = "withdraw";

    @Deprecated
    protected Contracts_SingleBungle_sol_SingleBungle(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Contracts_SingleBungle_sol_SingleBungle(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Contracts_SingleBungle_sol_SingleBungle(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Contracts_SingleBungle_sol_SingleBungle(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> enterRoom(String _member, BigInteger _value) {
        final Function function = new Function(
                FUNC_ENTERROOM, 
                Arrays.<Type>asList(new Address(160, _member),
                new Uint256(_value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> getBalance() {
        final Function function = new Function(FUNC_GETBALANCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> isSuccess(String _member, Boolean _flag) {
        final Function function = new Function(
                FUNC_ISSUCCESS, 
                Arrays.<Type>asList(new Address(160, _member),
                new org.web3j.abi.datatypes.Bool(_flag)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> leaveRoom(String _member, BigInteger _value) {
        final Function function = new Function(
                FUNC_LEAVEROOM, 
                Arrays.<Type>asList(new Address(160, _member),
                new Uint256(_value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> sgbgToken() {
        final Function function = new Function(FUNC_SGBGTOKEN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> withdraw(String _sender) {
        final Function function = new Function(
                FUNC_WITHDRAW, 
                Arrays.<Type>asList(new Address(160, _sender)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Contracts_SingleBungle_sol_SingleBungle load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Contracts_SingleBungle_sol_SingleBungle(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Contracts_SingleBungle_sol_SingleBungle load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Contracts_SingleBungle_sol_SingleBungle(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Contracts_SingleBungle_sol_SingleBungle load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Contracts_SingleBungle_sol_SingleBungle(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Contracts_SingleBungle_sol_SingleBungle load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Contracts_SingleBungle_sol_SingleBungle(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Contracts_SingleBungle_sol_SingleBungle> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _token, String _host, BigInteger _duration, BigInteger _minimumAmount) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Address(160, _token),
                new Address(160, _host),
                new Uint256(_duration),
                new Uint256(_minimumAmount)));
        return deployRemoteCall(Contracts_SingleBungle_sol_SingleBungle.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Contracts_SingleBungle_sol_SingleBungle> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _token, String _host, BigInteger _duration, BigInteger _minimumAmount) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Address(160, _token),
                new Address(160, _host),
                new Uint256(_duration),
                new Uint256(_minimumAmount)));
        return deployRemoteCall(Contracts_SingleBungle_sol_SingleBungle.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Contracts_SingleBungle_sol_SingleBungle> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _token, String _host, BigInteger _duration, BigInteger _minimumAmount) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Address(160, _token),
                new Address(160, _host),
                new Uint256(_duration),
                new Uint256(_minimumAmount)));
        return deployRemoteCall(Contracts_SingleBungle_sol_SingleBungle.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Contracts_SingleBungle_sol_SingleBungle> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _token, String _host, BigInteger _duration, BigInteger _minimumAmount) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Address(160, _token),
                new Address(160, _host),
                new Uint256(_duration),
                new Uint256(_minimumAmount)));
        return deployRemoteCall(Contracts_SingleBungle_sol_SingleBungle.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
