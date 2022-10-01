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
    public static final String BINARY = "60806040523480156200001157600080fd5b506040516200132e3803806200132e833981810160405281019062000037919062000182565b836000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555082600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508142620000c6919062000223565b60048190555080600381905550505050506200025e565b600080fd5b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b60006200010f82620000e2565b9050919050565b620001218162000102565b81146200012d57600080fd5b50565b600081519050620001418162000116565b92915050565b6000819050919050565b6200015c8162000147565b81146200016857600080fd5b50565b6000815190506200017c8162000151565b92915050565b600080600080608085870312156200019f576200019e620000dd565b5b6000620001af8782880162000130565b9450506020620001c28782880162000130565b9350506040620001d5878288016200016b565b9250506060620001e8878288016200016b565b91505092959194509250565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b6000620002308262000147565b91506200023d8362000147565b9250828201905080821115620002585762000257620001f4565b5b92915050565b6110c0806200026e6000396000f3fe6080604052600436106100555760003560e01c806312065fe01461005a57806318be0419146100855780633ccfd60b1461008f5780638492cbf71461009957806392cfe9dc146100c2578063eb9696da146100ed575b600080fd5b34801561006657600080fd5b5061006f6100f7565b60405161007c9190610938565b60405180910390f35b61008d61013c565b005b610097610368565b005b3480156100a557600080fd5b506100c060048036038101906100bb9190610990565b6104df565b005b3480156100ce57600080fd5b506100d7610598565b6040516100e49190610a3c565b60405180910390f35b6100f56105bc565b005b60008073ffffffffffffffffffffffffffffffffffffffff163073ffffffffffffffffffffffffffffffffffffffff16036101355760009050610139565b4790505b90565b60035434101561014d6003546107bf565b60405160200161015d9190610aee565b60405160208183030381529060405260405160200161017c9190610b3a565b604051602081830303815290604052906101cc576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016101c39190610bbb565b60405180910390fd5b506004544210610211576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161020890610c29565b60405180910390fd5b6002600081548092919061022490610c78565b919050555034600760003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055506001600860003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff02191690831515021790555060008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166323b872dd3330346040518463ffffffff1660e01b815260040161032293929190610ce1565b6020604051808303816000875af1158015610341573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906103659190610d2d565b50565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16146103f8576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016103ef90610da6565b60405180910390fd5b600454421161043c576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161043390610e12565b60405180910390fd5b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166323b872dd3033476040518463ffffffff1660e01b815260040161049993929190610ce1565b6020604051808303816000875af11580156104b8573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906104dc9190610d2d565b50565b42600554111580156104f2575060065442105b610531576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161052890610e7e565b60405180910390fd5b60001515811515036105955780600860003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff0219169083151502179055505b50565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000600760003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020541161063e576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161063590610eea565b60405180910390fd5b6004544210610682576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161067990610c29565b60405180910390fd5b6002600081548092919061069590610f0a565b919050555060008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166323b872dd3033600760003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020546040518463ffffffff1660e01b815260040161073693929190610ce1565b6020604051808303816000875af1158015610755573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906107799190610d2d565b50600760003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009055565b606060008203610806576040518060400160405280600181526020017f3000000000000000000000000000000000000000000000000000000000000000815250905061091a565b600082905060005b6000821461083857808061082190610c78565b915050600a826108319190610f62565b915061080e565b60008167ffffffffffffffff81111561085457610853610f93565b5b6040519080825280601f01601f1916602001820160405280156108865781602001600182028036833780820191505090505b5090505b600085146109135760018261089f9190610fc2565b9150600a856108ae9190610ff6565b60306108ba9190611027565b60f81b8183815181106108d0576108cf61105b565b5b60200101907effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916908160001a905350600a8561090c9190610f62565b945061088a565b8093505050505b919050565b6000819050919050565b6109328161091f565b82525050565b600060208201905061094d6000830184610929565b92915050565b600080fd5b60008115159050919050565b61096d81610958565b811461097857600080fd5b50565b60008135905061098a81610964565b92915050565b6000602082840312156109a6576109a5610953565b5b60006109b48482850161097b565b91505092915050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000819050919050565b6000610a026109fd6109f8846109bd565b6109dd565b6109bd565b9050919050565b6000610a14826109e7565b9050919050565b6000610a2682610a09565b9050919050565b610a3681610a1b565b82525050565b6000602082019050610a516000830184610a2d565b92915050565b7f4d494e494d554d20414d4f554e543a2000000000000000000000000000000000815250565b600081519050919050565b600081905092915050565b60005b83811015610ab1578082015181840152602081019050610a96565b60008484015250505050565b6000610ac882610a7d565b610ad28185610a88565b9350610ae2818560208601610a93565b80840191505092915050565b6000610af982610a57565b601082019150610b098284610abd565b915081905092915050565b7f2045544800000000000000000000000000000000000000000000000000000000815250565b6000610b468284610abd565b9150610b5182610b14565b60048201915081905092915050565b600082825260208201905092915050565b6000601f19601f8301169050919050565b6000610b8d82610a7d565b610b978185610b60565b9350610ba7818560208601610a93565b610bb081610b71565b840191505092915050565b60006020820190508181036000830152610bd58184610b82565b905092915050565b7f524f4f4d20434c4f534544000000000000000000000000000000000000000000600082015250565b6000610c13600b83610b60565b9150610c1e82610bdd565b602082019050919050565b60006020820190508181036000830152610c4281610c06565b9050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b6000610c838261091f565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8203610cb557610cb4610c49565b5b600182019050919050565b6000610ccb826109bd565b9050919050565b610cdb81610cc0565b82525050565b6000606082019050610cf66000830186610cd2565b610d036020830185610cd2565b610d106040830184610929565b949350505050565b600081519050610d2781610964565b92915050565b600060208284031215610d4357610d42610953565b5b6000610d5184828501610d18565b91505092915050565b7f4e4f5420484f5354204144445245535300000000000000000000000000000000600082015250565b6000610d90601083610b60565b9150610d9b82610d5a565b602082019050919050565b60006020820190508181036000830152610dbf81610d83565b9050919050565b7f524f4f4d204e4f5420434c4f5345442059455400000000000000000000000000600082015250565b6000610dfc601383610b60565b9150610e0782610dc6565b602082019050919050565b60006020820190508181036000830152610e2b81610def565b9050919050565b7f4e4f5420412053555256455920504552494f4400000000000000000000000000600082015250565b6000610e68601383610b60565b9150610e7382610e32565b602082019050919050565b60006020820190508181036000830152610e9781610e5b565b9050919050565b7f4e4f54204120524f4f4d204d454d424552000000000000000000000000000000600082015250565b6000610ed4601183610b60565b9150610edf82610e9e565b602082019050919050565b60006020820190508181036000830152610f0381610ec7565b9050919050565b6000610f158261091f565b915060008203610f2857610f27610c49565b5b600182039050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601260045260246000fd5b6000610f6d8261091f565b9150610f788361091f565b925082610f8857610f87610f33565b5b828204905092915050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b6000610fcd8261091f565b9150610fd88361091f565b9250828203905081811115610ff057610fef610c49565b5b92915050565b60006110018261091f565b915061100c8361091f565b92508261101c5761101b610f33565b5b828206905092915050565b60006110328261091f565b915061103d8361091f565b925082820190508082111561105557611054610c49565b5b92915050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fdfea264697066735822122054341b576c2e71b57f637b60f5c5ba2f768d7f2a13af8c5768bf3806beac636264736f6c63430008110033";

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

    public RemoteFunctionCall<TransactionReceipt> enterRoom() {
        final Function function = new Function(
                FUNC_ENTERROOM, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> getBalance() {
        final Function function = new Function(FUNC_GETBALANCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> isSuccess(Boolean _flag) {
        final Function function = new Function(
                FUNC_ISSUCCESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Bool(_flag)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> leaveRoom() {
        final Function function = new Function(
                FUNC_LEAVEROOM, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> sgbgToken() {
        final Function function = new Function(FUNC_SGBGTOKEN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> withdraw() {
        final Function function = new Function(
                FUNC_WITHDRAW, 
                Arrays.<Type>asList(), 
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
