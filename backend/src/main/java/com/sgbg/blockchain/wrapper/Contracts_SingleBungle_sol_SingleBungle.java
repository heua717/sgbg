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
    public static final String BINARY = "60806040523480156200001157600080fd5b506040516200144638038062001446833981810160405281019062000037919062000182565b836000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555082600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508142620000c6919062000223565b60048190555080600381905550505050506200025e565b600080fd5b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b60006200010f82620000e2565b9050919050565b620001218162000102565b81146200012d57600080fd5b50565b600081519050620001418162000116565b92915050565b6000819050919050565b6200015c8162000147565b81146200016857600080fd5b50565b6000815190506200017c8162000151565b92915050565b600080600080608085870312156200019f576200019e620000dd565b5b6000620001af8782880162000130565b9450506020620001c28782880162000130565b9350506040620001d5878288016200016b565b9250506060620001e8878288016200016b565b91505092959194509250565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b6000620002308262000147565b91506200023d8362000147565b9250828201905080821115620002585762000257620001f4565b5b92915050565b6111d8806200026e6000396000f3fe6080604052600436106100555760003560e01c806312065fe01461005a57806351cff8d914610085578063698a14fe146100a157806392cfe9dc146100ca578063b0f707dc146100f5578063c39e76cc14610111575b600080fd5b34801561006657600080fd5b5061006f61012d565b60405161007c9190610978565b60405180910390f35b61009f600480360381019061009a91906109f6565b610172565b005b3480156100ad57600080fd5b506100c860048036038101906100c39190610a5b565b6102ec565b005b3480156100d657600080fd5b506100df6103a6565b6040516100ec9190610afa565b60405180910390f35b61010f600480360381019061010a9190610b41565b6103ca565b005b61012b60048036038101906101269190610b41565b6105f8565b005b60008073ffffffffffffffffffffffffffffffffffffffff163073ffffffffffffffffffffffffffffffffffffffff160361016b576000905061016f565b4790505b90565b80600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1614610203576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016101fa90610bde565b60405180910390fd5b6004544211610247576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161023e90610c4a565b60405180910390fd5b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166323b872dd3084476040518463ffffffff1660e01b81526004016102a493929190610c79565b6020604051808303816000875af11580156102c3573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906102e79190610cc5565b505050565b42600554111580156102ff575060065442105b61033e576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161033590610d3e565b60405180910390fd5b60001515811515036103a25780600860008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff0219169083151502179055505b5050565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6003543410156103db6003546107ff565b6040516020016103eb9190610df5565b60405160208183030381529060405260405160200161040a9190610e41565b6040516020818303038152906040529061045a576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016104519190610eb1565b60405180910390fd5b50600454421061049f576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161049690610f1f565b60405180910390fd5b600260008154809291906104b290610f6e565b919050555080600760008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055506001600860008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff02191690831515021790555060008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166323b872dd8330846040518463ffffffff1660e01b81526004016105b093929190610c79565b6020604051808303816000875af11580156105cf573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906105f39190610cc5565b505050565b816000600760008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020541161067b576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161067290611002565b60405180910390fd5b60045442106106bf576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016106b690610f1f565b60405180910390fd5b600260008154809291906106d290611022565b919050555060008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166323b872dd3085600760008873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020546040518463ffffffff1660e01b815260040161077393929190610c79565b6020604051808303816000875af1158015610792573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906107b69190610cc5565b50600760008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009055505050565b606060008203610846576040518060400160405280600181526020017f3000000000000000000000000000000000000000000000000000000000000000815250905061095a565b600082905060005b6000821461087857808061086190610f6e565b915050600a82610871919061107a565b915061084e565b60008167ffffffffffffffff811115610894576108936110ab565b5b6040519080825280601f01601f1916602001820160405280156108c65781602001600182028036833780820191505090505b5090505b60008514610953576001826108df91906110da565b9150600a856108ee919061110e565b60306108fa919061113f565b60f81b8183815181106109105761090f611173565b5b60200101907effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916908160001a905350600a8561094c919061107a565b94506108ca565b8093505050505b919050565b6000819050919050565b6109728161095f565b82525050565b600060208201905061098d6000830184610969565b92915050565b600080fd5b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b60006109c382610998565b9050919050565b6109d3816109b8565b81146109de57600080fd5b50565b6000813590506109f0816109ca565b92915050565b600060208284031215610a0c57610a0b610993565b5b6000610a1a848285016109e1565b91505092915050565b60008115159050919050565b610a3881610a23565b8114610a4357600080fd5b50565b600081359050610a5581610a2f565b92915050565b60008060408385031215610a7257610a71610993565b5b6000610a80858286016109e1565b9250506020610a9185828601610a46565b9150509250929050565b6000819050919050565b6000610ac0610abb610ab684610998565b610a9b565b610998565b9050919050565b6000610ad282610aa5565b9050919050565b6000610ae482610ac7565b9050919050565b610af481610ad9565b82525050565b6000602082019050610b0f6000830184610aeb565b92915050565b610b1e8161095f565b8114610b2957600080fd5b50565b600081359050610b3b81610b15565b92915050565b60008060408385031215610b5857610b57610993565b5b6000610b66858286016109e1565b9250506020610b7785828601610b2c565b9150509250929050565b600082825260208201905092915050565b7f4e4f5420484f5354204144445245535300000000000000000000000000000000600082015250565b6000610bc8601083610b81565b9150610bd382610b92565b602082019050919050565b60006020820190508181036000830152610bf781610bbb565b9050919050565b7f524f4f4d204e4f5420434c4f5345442059455400000000000000000000000000600082015250565b6000610c34601383610b81565b9150610c3f82610bfe565b602082019050919050565b60006020820190508181036000830152610c6381610c27565b9050919050565b610c73816109b8565b82525050565b6000606082019050610c8e6000830186610c6a565b610c9b6020830185610c6a565b610ca86040830184610969565b949350505050565b600081519050610cbf81610a2f565b92915050565b600060208284031215610cdb57610cda610993565b5b6000610ce984828501610cb0565b91505092915050565b7f4e4f5420412053555256455920504552494f4400000000000000000000000000600082015250565b6000610d28601383610b81565b9150610d3382610cf2565b602082019050919050565b60006020820190508181036000830152610d5781610d1b565b9050919050565b7f4d494e494d554d20414d4f554e543a2000000000000000000000000000000000815250565b600081519050919050565b600081905092915050565b60005b83811015610db8578082015181840152602081019050610d9d565b60008484015250505050565b6000610dcf82610d84565b610dd98185610d8f565b9350610de9818560208601610d9a565b80840191505092915050565b6000610e0082610d5e565b601082019150610e108284610dc4565b915081905092915050565b7f2045544800000000000000000000000000000000000000000000000000000000815250565b6000610e4d8284610dc4565b9150610e5882610e1b565b60048201915081905092915050565b6000601f19601f8301169050919050565b6000610e8382610d84565b610e8d8185610b81565b9350610e9d818560208601610d9a565b610ea681610e67565b840191505092915050565b60006020820190508181036000830152610ecb8184610e78565b905092915050565b7f524f4f4d20434c4f534544000000000000000000000000000000000000000000600082015250565b6000610f09600b83610b81565b9150610f1482610ed3565b602082019050919050565b60006020820190508181036000830152610f3881610efc565b9050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b6000610f798261095f565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8203610fab57610faa610f3f565b5b600182019050919050565b7f4e4f54204120524f4f4d204d454d424552000000000000000000000000000000600082015250565b6000610fec601183610b81565b9150610ff782610fb6565b602082019050919050565b6000602082019050818103600083015261101b81610fdf565b9050919050565b600061102d8261095f565b9150600082036110405761103f610f3f565b5b600182039050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601260045260246000fd5b60006110858261095f565b91506110908361095f565b9250826110a05761109f61104b565b5b828204905092915050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b60006110e58261095f565b91506110f08361095f565b925082820390508181111561110857611107610f3f565b5b92915050565b60006111198261095f565b91506111248361095f565b9250826111345761113361104b565b5b828206905092915050565b600061114a8261095f565b91506111558361095f565b925082820190508082111561116d5761116c610f3f565b5b92915050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fdfea264697066735822122061df8231bc9e53e26f243d96571a4dc1eac7275b0c7e9cffbd0ec9a88fcca7eb64736f6c63430008110033";

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
